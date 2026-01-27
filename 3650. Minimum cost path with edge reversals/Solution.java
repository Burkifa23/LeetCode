import java.util.*;


class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();

        for(int i =0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new int[]{v,w});
            graph.get(v).add(new int[]{u, 2*w});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0]=0;

        pq.offer(new int[]{0,0});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0];
            int u = current[1];

            if (u==n-1){
                return cost;
            }

            if (cost>dist[u]){
                continue;
            }

            for (int[] neighbor: graph.get(u)){
                int v = neighbor[0];
                int weight = neighbor[1];

                if( dist[u]+weight <dist[v]) {
                    dist[v] = dist[u]+weight;
                    pq.offer(new int[]{dist[v],v});
                }
            }
        }
        return -1;
    }
}
