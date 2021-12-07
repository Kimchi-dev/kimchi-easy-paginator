package me.kimchi.pagination;

import me.kimchi.pagination.constant.PaginatorConstant;
import me.kimchi.pagination.object.PaginatedObject;
import me.kimchi.pagination.paginator.KimchiPaginator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Throwable {

        manyTimesAtOnce(12,10);

    }


    private static void manyTimesAtOnce (final int listSize, final int sizeAtOnce) throws Throwable {

        List<String> nums = IntStream.range(1, listSize + 1).mapToObj(num -> "" + num).collect(Collectors.toList());

        String [] array;
        int race = 1;
        for(int i = 0;i < nums.size();i += sizeAtOnce) {

            int mod = nums.size() % sizeAtOnce;
            if(mod != 0 && i == (nums.size() - mod))
                array = new String [mod];
            else
                array = new String [sizeAtOnce];

            int temp = i;
            for(int j = i; j < (temp + array.length);j++){

                array[j - temp] = nums.get(j);
            }

            System.out.println(Arrays.toString(array) + " size = "+array.length+", race = "+race);

            array = null;
            race++;
        }

    }
}
