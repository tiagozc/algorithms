package dev.tiagozc.dynamicconnectivity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class WeightedQuickUnionPathCompressionUFTest {

  @Test
  void pathCompressionQuickUnionConnectedUFTest() {

    var pathCompression = new WeightedQuickUnionPathCompressionUF(10);

    assertThat(pathCompression.sz.length).isEqualTo(10);
    assertThat(pathCompression.id.length).isEqualTo(10);
    assertThat(pathCompression.sz).containsExactly(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    assertThat(pathCompression.id).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    assertThat(pathCompression.connected(4, 3)).isFalse();
    assertThat(pathCompression.connected(3, 8)).isFalse();
    assertThat(pathCompression.connected(6, 5)).isFalse();
    assertThat(pathCompression.connected(9, 4)).isFalse();
    assertThat(pathCompression.connected(2, 1)).isFalse();
    assertThat(pathCompression.connected(5, 0)).isFalse();
    assertThat(pathCompression.connected(7, 2)).isFalse();
    assertThat(pathCompression.connected(6, 1)).isFalse();
    assertThat(pathCompression.connected(7, 3)).isFalse();

    pathCompression.union(4, 3); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(0, 1, 2, 4, 4, 5, 6, 7, 8, 9);
    assertThat(pathCompression.sz).containsExactly(1, 1, 1, 1, 2, 1, 1, 1, 1, 1);

    pathCompression.union(3, 8); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(0, 1, 2, 4, 4, 5, 6, 7, 4, 9);
    assertThat(pathCompression.sz).containsExactly(1, 1, 1, 1, 3, 1, 1, 1, 1, 1);

    pathCompression.union(6, 5); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(0, 1, 2, 4, 4, 6, 6, 7, 4, 9);
    assertThat(pathCompression.sz).containsExactly(1, 1, 1, 1, 3, 1, 2, 1, 1, 1);

    pathCompression.union(9, 4); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(0, 1, 2, 4, 4, 6, 6, 7, 4, 4);
    assertThat(pathCompression.sz).containsExactly(1, 1, 1, 1, 4, 1, 2, 1, 1, 1);

    pathCompression.union(2, 1); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(0, 2, 2, 4, 4, 6, 6, 7, 4, 4);
    assertThat(pathCompression.sz).containsExactly(1, 1, 2, 1, 4, 1, 2, 1, 1, 1);

    pathCompression.union(5, 0); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(6, 2, 2, 4, 4, 6, 6, 7, 4, 4);
    assertThat(pathCompression.sz).containsExactly(1, 1, 2, 1, 4, 1, 3, 1, 1, 1);

    pathCompression.union(7, 2); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(6, 2, 2, 4, 4, 6, 6, 2, 4, 4);
    assertThat(pathCompression.sz).containsExactly(1, 1, 3, 1, 4, 1, 3, 1, 1, 1);

    pathCompression.union(6, 1); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(6, 2, 6, 4, 4, 6, 6, 2, 4, 4);
    assertThat(pathCompression.sz).containsExactly(1, 1, 3, 1, 4, 1, 6, 1, 1, 1);

    // PATH COMPRESSION
    // 7 becomes child of 6 during union
    pathCompression.union(7, 3); // -------------- 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    assertThat(pathCompression.id).containsExactly(6, 2, 6, 4, 6, 6, 6, 6, 4, 4);
    assertThat(pathCompression.sz).containsExactly(1, 1, 3, 1, 4, 1, 10, 1, 1, 1);

    assertThat(pathCompression.connected(4, 3)).isTrue();
    assertThat(pathCompression.connected(3, 8)).isTrue();
    assertThat(pathCompression.connected(6, 5)).isTrue();
    assertThat(pathCompression.connected(9, 4)).isTrue();
    assertThat(pathCompression.connected(2, 1)).isTrue();
    assertThat(pathCompression.connected(5, 0)).isTrue();
    assertThat(pathCompression.connected(7, 2)).isTrue();
    assertThat(pathCompression.connected(6, 1)).isTrue();
    assertThat(pathCompression.connected(7, 3)).isTrue();
  }
}
