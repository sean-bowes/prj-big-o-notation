package com.koisoftware.lists;

import com.koisoftware.linkedlists.SinglyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> tested;

    @BeforeEach
    public void setup() {
        tested = new SinglyLinkedList<Integer>();
    }

    @Test
    public void reverseEmptyList() {
        tested.reverse();
        assertEquals(0, tested.size());
    }
}
