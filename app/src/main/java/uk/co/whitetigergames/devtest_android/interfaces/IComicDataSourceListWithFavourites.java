package uk.co.whitetigergames.devtest_android.interfaces;

public interface IComicDataSourceListWithFavourites extends IComicDataSourceList
{
    void ToggleFavourite(int ID);
    boolean IsFavourite(int position);

    int[] getFavourites();
}
