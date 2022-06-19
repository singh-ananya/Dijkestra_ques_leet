//Path With Minimum Effort
//leet 1631
class Solution {
    int[][] dirs = new int[][] { 
        { 1, 0 }, 
        {-1, 0 },
        { 0, 1 },
        { 0,-1 }
    };
    public int minimumEffortPath(int[][] heights) {
        int n=heights.length;
        int m=heights[0].length;
        int dis[][]=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dis[i][j]=(int)(1e8);
            }
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>((int[] a,int[]b)->{
            return a[2]-b[2];
        });
        pq.add(new int[]{0,0,0});
        dis[0][0]=0;
        while(pq.size()>0){
            int temp[]=pq.remove();
            int i=temp[0];
            int j=temp[1];
            int effort_till_now=temp[2];
            if(i==m-1 && j==n-1)
                return dis[m-1][n-1];
            if(dis[i][j]<effort_till_now)
                continue;
            for(int[] dir:dirs){
                int x=i+dir[0];
                int y=j+dir[1];
                if(x>=0 && y>=0 && x<n && y<m){
                    int curr_effort=Math.abs(heights[i][j]-heights[x][y]);
                    int final_effort=Math.max(curr_effort,effort_till_now);
                    if(dis[x][y]>final_effort){
                       dis[x][y]=final_effort;
                        pq.add(new int[]{x,y,final_effort});
                    }
                }
            }
        }
        return dis[n-1][m-1];
    }
}