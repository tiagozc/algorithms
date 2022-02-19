package dev.tiagozc.dynamicconnectivity;

/**
 * Same idea as {@link QuickUnionUF}, but maintain extra array to count number of objects in the
 * tree. Size defines who connects to whom, consequently it improves speed cause it prevents tall
 * trees so root is more performant.
 *
 * <p><b>Note</b> Although it may seem weird to keep a track of number of objects in the tree and
 * not its height it makes sense.
 *
 * <p>For N = 3 - 0, 1, 2, 3, and union of (0, 1), union (1, 2), union (2, 3) in an attempt to make
 * the most possible taller tree, the 2nd union of (1,2) will already be balanced.
 */
public class WeightedQuickUnionUF {

  protected final int[] id;
  protected final int[] sz;

  public WeightedQuickUnionUF(int n) {
    this.id = new int[n];
    this.sz = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }

  /** Changes the root of p to point to the root of q */
  public void union(int p, int q) {
    int pRoot = root(p);
    int qRoot = root(q);

    if (pRoot == qRoot) return;

    // if tree size of p is less than tree size of q
    if (sz[pRoot] < sz[qRoot]) {
      id[pRoot] = qRoot; // then connect tree p to q
      sz[qRoot] += sz[pRoot]; // and update q size with p size length
    } else {
      id[qRoot] = pRoot; // connect tree q to p
      sz[pRoot] += sz[qRoot]; // update p size with new q size child
    }
  }

  public int root(int i) {
    while (i != id[i]) i = id[i];
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }
}
