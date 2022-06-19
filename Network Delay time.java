//743. Network Delay Time
//================================
class Solution {
    class Edge{
        int v;
        int wt;
        Edge(int v,int wt){
            this.v=v;
            this.wt=wt;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Edge>> graph=new ArrayList<>();
        for(int i=0;i<=n;i++){
           graph.add(new ArrayList<>()); 
        }
        for(int[] t:times){
            int u=t[0];
            int v=t[1];
            int wt=t[2];
            graph.get(u).add(new Edge(v,wt));
        }
        PriorityQueue<Edge> pq=new PriorityQueue<>((Edge a, Edge b)->{
            return a.wt-b.wt;
        });
        pq.add(new Edge(k,0));
        int []dist=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[k]=0;
        while(pq.size()>0){
            Edge edge=pq.remove();
            int node=edge.v;
            int wsf=edge.wt;
            if(dist[node]<wsf)
                continue;
            for(Edge e:graph.get(node)){
                if(wsf+e.wt<dist[e.v]){
                    dist[e.v]=wsf+e.wt;
                    pq.add(new Edge(e.v,dist[e.v]));
                }
            }
        }
        int ans=-1;
        for(int i=1;i<=n;i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            ans=Math.max(ans,dist[i]);
        }
        return ans;
        
    }
}