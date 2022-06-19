//787. Cheapest Flights Within K Stops
class Solution {
    class Edge{
        int v;
        int wt;
        Edge(int v,int wt){
            this.v=v;
            this.wt=wt;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Edge>> graph=new ArrayList<>();
        //int n=flights.length;
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] t:flights){
            int u=t[0];
            int v=t[1];
            int wt=t[2];
            graph.get(u).add(new Edge(v,wt));
        } 
        int dist[]=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        //int cnt=-1;
        dist[src]=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
            if(a[2]==b[2])
                return a[1]-b[1];
            return a[2]-b[2];
        });
        pq.add(new int[]{src,0,0});
        while(pq.size()>0){
            
            int[] edge=pq.remove();
            int node=edge[0];
            int price=edge[1];
            int stops=edge[2];
            if(stops>k)
                continue;
            for(Edge nbr:graph.get(node)){
                if(price+nbr.wt<dist[nbr.v]){
                   dist[nbr.v]=price+nbr.wt;
                    pq.add(new int[]{nbr.v,price+nbr.wt,stops+1});
                }
            }
            
        }
        return dist[dst] != Integer.MAX_VALUE ? dist[dst] : -1;
        
    }
}