package dev.tiagozc.dynamicconnectivity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class WeightedQuickUnionUFTest {

  @Test
  void weightedQuickUnionConnectedUFTest() {

    var weightedQuickUnion = new WeightedQuickUnionUF(10);

    assertThat(weightedQuickUnion.sz.length).isEqualTo(10);
    assertThat(weightedQuickUnion.id.length).isEqualTo(10);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    assertThat(weightedQuickUnion.id).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    assertThat(weightedQuickUnion.connected(4, 3)).isFalse();
    assertThat(weightedQuickUnion.connected(3, 8)).isFalse();
    assertThat(weightedQuickUnion.connected(6, 5)).isFalse();
    assertThat(weightedQuickUnion.connected(9, 4)).isFalse();
    assertThat(weightedQuickUnion.connected(2, 1)).isFalse();
    assertThat(weightedQuickUnion.connected(5, 0)).isFalse();
    assertThat(weightedQuickUnion.connected(7, 2)).isFalse();
    assertThat(weightedQuickUnion.connected(6, 1)).isFalse();
    assertThat(weightedQuickUnion.connected(7, 3)).isFalse();

    weightedQuickUnion.union(4, 3); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(0, 1, 2, 4, 4, 5, 6, 7, 8, 9);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 1, 1, 2, 1, 1, 1, 1, 1);

    weightedQuickUnion.union(3, 8); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(0, 1, 2, 4, 4, 5, 6, 7, 4, 9);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 1, 1, 3, 1, 1, 1, 1, 1);

    weightedQuickUnion.union(6, 5); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(0, 1, 2, 4, 4, 6, 6, 7, 4, 9);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 1, 1, 3, 1, 2, 1, 1, 1);

    weightedQuickUnion.union(9, 4); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(0, 1, 2, 4, 4, 6, 6, 7, 4, 4);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 1, 1, 4, 1, 2, 1, 1, 1);

    weightedQuickUnion.union(2, 1); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(0, 2, 2, 4, 4, 6, 6, 7, 4, 4);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 2, 1, 4, 1, 2, 1, 1, 1);

    weightedQuickUnion.union(5, 0); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(6, 2, 2, 4, 4, 6, 6, 7, 4, 4);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 2, 1, 4, 1, 3, 1, 1, 1);

    weightedQuickUnion.union(7, 2); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(6, 2, 2, 4, 4, 6, 6, 2, 4, 4);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 3, 1, 4, 1, 3, 1, 1, 1);

    weightedQuickUnion.union(6, 1); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(6, 2, 6, 4, 4, 6, 6, 2, 4, 4);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 3, 1, 4, 1, 6, 1, 1, 1);

    weightedQuickUnion.union(7, 3); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(weightedQuickUnion.id).containsExactly(6, 2, 6, 4, 6, 6, 6, 2, 4, 4);
    assertThat(weightedQuickUnion.sz).containsExactly(1, 1, 3, 1, 4, 1, 10, 1, 1, 1);

    assertThat(weightedQuickUnion.connected(4, 3)).isTrue();
    assertThat(weightedQuickUnion.connected(3, 8)).isTrue();
    assertThat(weightedQuickUnion.connected(6, 5)).isTrue();
    assertThat(weightedQuickUnion.connected(9, 4)).isTrue();
    assertThat(weightedQuickUnion.connected(2, 1)).isTrue();
    assertThat(weightedQuickUnion.connected(5, 0)).isTrue();
    assertThat(weightedQuickUnion.connected(7, 2)).isTrue();
    assertThat(weightedQuickUnion.connected(6, 1)).isTrue();
    assertThat(weightedQuickUnion.connected(7, 3)).isTrue();
  }
}
