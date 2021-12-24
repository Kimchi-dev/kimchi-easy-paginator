package com.github.kimchidev.pagination.calculator;

abstract class IndexCalculator {


    protected abstract int calStartIndex(int currentPage, int contentsPerPage);
    protected abstract int calEndIndex(int currentPage, int startIndex, int contentsPerPage);

    public static class MysqlIndexCalculator extends IndexCalculator {

        @Override
        protected int calStartIndex(int currentPage, int contentsPerPage) {
            int startIndex = (currentPage * contentsPerPage) - contentsPerPage;

            return startIndex;
        }

        @Override
        protected  int calEndIndex(int currentPage, int startIndex, int contentsPerPage) {

            return contentsPerPage;
        }
    }

    public static class OracleIndexCalculator extends IndexCalculator {
        @Override
        protected int calStartIndex(int currentPage, int contentsPerPage) {
            int startIndex = (currentPage * contentsPerPage) - contentsPerPage;

            return (startIndex + 1);
        }

        @Override
        protected int calEndIndex(int currentPage, int startIndex, int contentsPerPage) {
            return (startIndex + contentsPerPage) - 1;
        }
    }

    public static class PostgreSqlIndexCalculator extends  IndexCalculator {

        @Override
        protected int calStartIndex(int currentPage, int numSizePerPage) {

            return numSizePerPage;
        }

        @Override
        protected int calEndIndex(int currentPage, int startIndex, int contentsPerPage) {
            int endIndex = (currentPage * contentsPerPage) - contentsPerPage;

            return endIndex;
        }
    }
}
