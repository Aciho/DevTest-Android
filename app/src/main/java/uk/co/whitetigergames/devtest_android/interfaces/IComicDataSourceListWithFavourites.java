package uk.co.whitetigergames.devtest_android.interfaces;

public interface IComicDataSourceListWithFavourites extends IComicDataSourceList
{
    void toggleFavourite(int ID);
    boolean isFavourite(int position);

    int[] getFavourites();
}
