package dev.tiagozc.dynamicconnectivity;

/**
 * Keep connected objects by a set of trees. The value in the array represents the parent root of
 * this object. Objects with the same root are connected.
 */
public class QuickUnionUF {

  protected final int[] id;

  public QuickUnionUF(int n) {
    this.id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  /** Changes the root of p to point to the root of q */
  public void union(int p, int q) {
    int pRoot = root(p);
    int qRoot = root(q);
    id[pRoot] = qRoot;
  }

  public int root(int i) {
    while (i != id[i]) i = id[i];
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }
}
