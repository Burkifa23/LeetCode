import java.util.ArrayList;
import java.util.List;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;
        char[] word = new char[len];

        for (int i = 0; i < len; i++) {
            word[i] = '?';
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != '?' && word[i + j] != str2.charAt(j)) {
                        return "";
                    }
                    word[i + j] = str2.charAt(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean has_q = false;
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?') {
                        has_q = true;
                        break;
                    }
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (!has_q && match) {
                    return "";
                }
            }
        }

        List<Integer> qList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (word[i] == '?') {
                qList.add(i);
            }
        }

        int qSize = qList.size();
        if (qSize == 0) {
            return new String(word);
        }

        int[] state = new int[qSize];
        int ptr = 0;

        while (ptr >= 0 && ptr < qSize) {
            int k = qList.get(ptr);
            int k_next = (ptr + 1 < qSize) ? qList.get(ptr + 1) : Integer.MAX_VALUE;

            boolean[] forbidden = new boolean[26];
            int min_i = Math.max(0, k - m + 1);
            int max_i = Math.min(n - 1, k);

            for (int i = min_i; i <= max_i; i++) {
                if (str1.charAt(i) == 'F') {
                    if (k_next > i + m - 1) {
                        boolean match = true;
                        for (int j = 0; j < m; j++) {
                            if (i + j == k) continue;
                            if (word[i + j] != str2.charAt(j)) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            forbidden[str2.charAt(k - i) - 'a'] = true;
                        }
                    }
                }
            }

            int c = state[ptr];
            while (c < 26 && forbidden[c]) {
                c++;
            }

            if (c < 26) {
                word[k] = (char) ('a' + c);
                state[ptr] = c + 1;
                ptr++;
                if (ptr < qSize) {
                    state[ptr] = 0;
                }
            } else {
                word[k] = '?';
                state[ptr] = 0;
                ptr--;
            }
        }

        if (ptr < 0) {
            return "";
        }

        return new String(word);
    }
}
