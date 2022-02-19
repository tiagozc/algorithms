package dev.tiagozc.dynamicconnectivity;

/**
 * The final optimal solution.
 *
 * <p>Same idea as {@link WeightedQuickUnionUF}, but with 1 major improvement. Not only it keeps
 * tree smaller, but it also flattens the tree applying path compression.
 *
 * <p>The path compression consists in making every node in the path to point directly to the root.
 * In this case, we would have to find the root of the path, and later update each node in the path
 * to point to the found root. As that approach would consist in another iteration, to prevent that
 * we can use the already existing iteration to find the root and flat the path making every node to
 * point to their grandparent. Not as efficient as the full path compression in theory, but in
 * practice as good as well.
 */
public class WeightedQuickUnionPathCompressionUF {

  protected final int[] id;
  protected final int[] sz;

  public WeightedQuickUnionPathCompressionUF(int n) {
    this.id = new int[n];
    this.sz = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }

  public void union(int p, int q) {
    int pRoot = root(p);
    int qRoot = root(q);

    if (pRoot == qRoot) return;

    if (sz[pRoot] < sz[qRoot]) {
      id[pRoot] = qRoot;
      sz[qRoot] += sz[pRoot];
    } else {
      id[qRoot] = pRoot;
      sz[pRoot] += sz[qRoot];
    }
  }

  public int root(int i) {
    while (i != id[i]) {
      id[i] = id[id[i]]; // path compression, make i point to its grandparent
      i = id[i];
    }
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }
}
