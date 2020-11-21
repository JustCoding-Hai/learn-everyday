package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit4.*;
import org.mockito.InOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class DemoApplicationTests {

    /**
     * Verify some behavior
     */
	@Test
	void test01(){
        List mockedList = mock(List.class);
        //using mock object 使用mock对象
        mockedList.add("one");
        mockedList.add("two");
        mockedList.clear();

        //verification 验证,不验证操作的顺序
        verify(mockedList).add("two");
        verify(mockedList).add("one");

        verify(mockedList).clear();
    }

    /**
     * do some stubbing（测试桩，设置默认返回的值）
     */
    @Test
    void test2(){
        //mock concrete class
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing:set stable value for class
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(RuntimeException.class);
        when(mockedList.get(0)).thenReturn("two");
        //prints value
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        verify(mockedList).get(0);
    }

    /**
     * Argument matchers
     */
    @Test
    void test3(){
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("This is a element");
      //  when(mockedList.contains(argThat(isValid()))).thenReturn(true);

        System.out.println(mockedList.get(996));

        verify(mockedList).get(anyInt());

      //  verify(mockedList).someMethod
    }

    /**
     * Verifying exact/at least x/never number of invocations
     * 验证确切/最少/从不 调用的次数
     */
    @Test
    void test4(){
        LinkedList mockedList = mock(LinkedList.class);
        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");

        verify(mockedList,times(6)).add(anyString());

    }

    /**
     * 为返回值为void的方法使用存根来设置调用后抛出异常
     * Stubbing void methods with exceptions
     */
    @Test
    public void test5(){
        ArrayList mockedList = mock(ArrayList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.add(2);
        mockedList.clear();
        System.out.println(mockedList.get(0));

    }

    /**
     * Verification in order
     * 验证 操作的顺序
     */
    @Test
    public void test6(){
        //A.single mock whose methods must be invokes in a particular order
        ArrayList singleMock= mock(ArrayList.class);
        singleMock.add("I am N0.1");
        singleMock.add("I am N0.2");

        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("I am N0.1");
        inOrder.verify(singleMock).add("I am N0.2");

        //B.Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("first called firstMock");
        secondMock.add("second  called secondMock");

        InOrder inOrder1 = inOrder(firstMock, secondMock);

        inOrder1.verify(firstMock).add("first called firstMock");
        inOrder1.verify(secondMock).add("second called secondMock");
    }

    /**
     *  Making sure interaction(s) never happened on mock
     */
    @Test
    public void test7(){
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        mockOne.add("one");

        verify(mockOne,never()).add("one");

        verifyZeroInteractions(mockTwo,mockThree);

    }

    /**
     * Finding redundant invocations
     */
    @Test
    public void test8(){
        List mock = mock(List.class);
        mock.add(1);
        mock.add(1);
        verifyNoMoreInteractions(mock);
        AssertEquals.assertEquals();
    }



	@Test
	void contextLoads() {
	}

}
