package dev.tiagozc.dynamicconnectivity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class QuickUnionUFTest {

  @Test
  void quickUnionConnectedTest() {

    var quickUnion = new QuickUnionUF(10);

    assertThat(quickUnion.id.length).isEqualTo(10);
    assertThat(quickUnion.id).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    quickUnion.union(4, 3); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 2, 3, 3, 5, 6, 7, 8, 9);

    quickUnion.union(3, 8); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 2, 8, 3, 5, 6, 7, 8, 9);

    quickUnion.union(6, 5); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 2, 8, 3, 5, 5, 7, 8, 9);

    quickUnion.union(9, 4); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 2, 8, 3, 5, 5, 7, 8, 8);

    quickUnion.union(2, 1); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 1, 8, 3, 5, 5, 7, 8, 8);

    assertThat(quickUnion.connected(8, 9)).isTrue();
    assertThat(quickUnion.connected(5, 4)).isFalse();

    quickUnion.union(5, 0); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 1, 8, 3, 0, 5, 7, 8, 8);

    quickUnion.union(7, 2); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(0, 1, 1, 8, 3, 0, 5, 1, 8, 8);

    // first time that if we don't connect via root of p and q the connection is lost (try draw)
    quickUnion.union(6, 1); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(1, 1, 1, 8, 3, 0, 5, 1, 8, 8);

    quickUnion.union(7, 3); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(quickUnion.id).containsExactly(1, 8, 1, 8, 3, 0, 5, 1, 8, 8);
  }
}
