import java.util.Arrays;

Class Solution{
  public int[] sortByBits(int[] arr){
    for(int i=0; i<arr.length;i++){
      arr[i] |= (Integer.bitCount(arr[i])<<16);
    }

    Arrays.sort(arr);

    for(int i=0; i<arr.length;i++){
      arr[i] &= 0XFFFF;
    }

    return arr;    
}
