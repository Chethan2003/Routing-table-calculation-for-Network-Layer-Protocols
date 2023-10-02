# Routing-table-calculation-for-Network-Layer-Protocols

Data is exchanged between end systems in the realm of computer networks after passing 
through numerous layers and being converted into packets. The message travels through 
routers at the Network layer, which facilitates message transmission. During this 
procedure, a packet with an IP address associated is given to the message. Similar to a 
mailing address is the IP address. The IP address-containing packet is referred to as a 
datagram. In this layer, the routing component is used.
With the aid of forwarding / routing tables, a packet is routed, hop by hop, from its source 
to its destination in routing. A packet is routed when it is transferred from a source router 
to a destination router. The question is which other routers a packet should traverse in 
addition to the source and destination routers. The Routing Table assists and offers details 
on the network topology. A packet can take a number of routes to go from its source to its 
destination. Here, the routing table chooses the path that a specific packet should travel.
In this project, we are going to calculate the Routing table for the given inputs using basic 
Routing algorithms namely Distance Vector Routing Protocol and Link State Routing 
Protocol.
Distance Vector Routing Protocol (DVRP) each node creates is its own least-cost tree 
with the minimum information it has about its immediate neighbors. These trees are then 
shared between their immediate neighbors to make the trees more complete. The core 
concept needed is Bellman-Ford Algorithm (BFA). The BFA here is used to find the 
shortest path between the source node and the destination node.
In Link State Routing Protocol (LSRP), the method in which each node shares its 
neighbor’s knowledge with every other router on the internet. Each router in the network 
understands the network topology and then makes a routing table depend on this 
topology. The core concept needed is Dijkstra’s Algorithm.
