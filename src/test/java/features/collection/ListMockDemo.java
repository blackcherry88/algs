package features.collection;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ListMockDemo {
    @Test
    public void mockList() {
        List<String> mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList, times(1)).add(anyString());
        verify(mockedList).clear();
    }

    @Test
    public void mockStub() {
        // you can mock concrete classes, not only interfaces
        LinkedList<String> mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");
        assertEquals("first", mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        assertNull(mockedList.get(999));
    }
}
