Team members: Abigail Poole, Logan Stapleton, Shiyu Lin

This project creates a network of words commonly used in hateful online posts and attempts to find unknown slurs and trigger words by how well-connected a word is to a known slur or trigger word.


· TextGraph.java is the main function to create a text network and run the spreading activation algorithm.

· EdgeWeightedGraph.java is the primary data structure, i.e. a fully-weighted, undirected graph.

· TestEWG.java tests out the EdgeWeightedGraph class on a simple graph.

· Vertex.java creates a vertex used in the EdgeWeightedGraph.

· Edge.java creates an edge used in the EdgeWeightedGraph.

· ReadFile.java is a class that allows us to read text files, split them by lines, and split the lines into words.

· text_files is a folder containing test text (gettysburg), a file used in a method (common), and scraped chimpamania.com posts.
