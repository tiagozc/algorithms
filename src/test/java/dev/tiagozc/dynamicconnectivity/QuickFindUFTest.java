package dev.tiagozc.dynamicconnectivity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class QuickFindUFTest {

  @Test
  void quickFindUnionConnectedTest() {

    var quickFind = new QuickFindUF(10);

    assertThat(quickFind.id.length).isEqualTo(10);
    assertThat(quickFind.id).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    quickFind.union(4, 3);
    assertThat(quickFind.id).containsExactly(0, 1, 2, 3, 3, 5, 6, 7, 8, 9);

    quickFind.union(3, 8);
    assertThat(quickFind.id).containsExactly(0, 1, 2, 8, 8, 5, 6, 7, 8, 9);

    quickFind.union(6, 5);
    assertThat(quickFind.id).containsExactly(0, 1, 2, 8, 8, 5, 5, 7, 8, 9);

    quickFind.union(9, 4);
    assertThat(quickFind.id).containsExactly(0, 1, 2, 8, 8, 5, 5, 7, 8, 8);

    quickFind.union(2, 1);
    assertThat(quickFind.id).containsExactly(0, 1, 1, 8, 8, 5, 5, 7, 8, 8);

    assertThat(quickFind.connected(8, 9)).isTrue();
    assertThat(quickFind.connected(5, 0)).isFalse();

    quickFind.union(5, 0);
    assertThat(quickFind.id).containsExactly(0, 1, 1, 8, 8, 0, 0, 7, 8, 8);

    quickFind.union(7, 2);
    assertThat(quickFind.id).containsExactly(0, 1, 1, 8, 8, 0, 0, 1, 8, 8);

    quickFind.union(6, 1);
    assertThat(quickFind.id).containsExactly(1, 1, 1, 8, 8, 1, 1, 1, 8, 8);
  }
}
