package features.collection;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class GuavaCollectionTest {
    @Test
    public void testListMock() {
        //mock creation
        List<String> mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void GuavaImmutableTest() {
        ImmutableSet<String> color_map = ImmutableSet.of(
                "red",
                "orange",
                "green",
                "blue",
                "purple"
        );
        // assertThrows(UnsupportedOperationException.class, () -> color_map.add("green"));
        assertEquals(color_map.size(), 5);
    }

}
