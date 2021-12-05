package me.kimchi.pagination.calculator;

public abstract class IndexCalculator {


    protected abstract int calStartIndex(int currentPage, int numSizePerPage);
    protected abstract int calEndIndex(int currentPage, int numSizePerPage, int startIndex, int contentsPerPage);

    public static class MysqlIndexCalculator extends IndexCalculator {

        @Override
        protected int calStartIndex(int currentPage, int numSizePerPage) {
            int startIndex = (currentPage * numSizePerPage) - numSizePerPage;

            return startIndex;
        }

        @Override
        protected  int calEndIndex(int currentPage, int numSizePerPage, int stargIndex, int contentsPerPage) {

            return numSizePerPage;
        }
    }

    public static class OracleIndexCalculator extends IndexCalculator {
        @Override
        protected int calStartIndex(int currentPage, int numSizePerPage) {
            int startIndex = (currentPage * numSizePerPage) - numSizePerPage;

            return (startIndex + 1);
        }

        @Override
        protected int calEndIndex(int currentPage, int numSizePerPage, int startIndex, int contentsPerPage) {
            return (startIndex + contentsPerPage) - 1;
        }
    }

    public static class PostgreSqlIndexCalculator extends  IndexCalculator {

        @Override
        protected int calStartIndex(int currentPage, int numSizePerPage) {

            return numSizePerPage;
        }

        @Override
        protected int calEndIndex(int currentPage, int numSizePerPage, int startIndex, int contentsPerPage) {
            int endIndex = (currentPage * numSizePerPage) - numSizePerPage;

            return endIndex;
        }
    }
}
