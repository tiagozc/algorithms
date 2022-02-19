package dev.tiagozc.dynamicconnectivity;

/**
 * Keep connected objects in the same group. The value in the array represents the set or group that
 * the object belongs to. The group identifier is the initial object identifier. When 2 objects
 * connect, we update all the objects in the same group.
 *
 * <p>Union too expensive, as more unions, more n access to the array, n²
 */
public class QuickFindUF {

  protected int[] id;

  public QuickFindUF(int n) {
    this.id = new int[n];
    for (int i = 0; i < n; i++) {
      this.id[i] = i;
    }
  }

  /** Connects 2 objects */
  public void union(int p, int q) {
    int qid = id[q]; // connecting to
    int pid = id[p]; // been connected

    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) id[i] = qid;
    }
  }

  /** Quick find */
  public boolean connected(int p, int q) {
    return this.id[p] == this.id[q];
  }
}
