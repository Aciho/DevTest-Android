package uk.co.whitetigergames.devtest_android.interfaces;

public interface IComicDataSourceList
{
    int getCount();
    IComicDataSource getData(int position);
    int getPublisherCount(String publisher);

}
