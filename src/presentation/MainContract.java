package presentation;

import java.io.IOException;

public interface MainContract {
    interface View {
        void setAvailableAssets(String assets);
        void addCoin(String coinInfo);
        void deleteCoin(int idx);
        void setSelectedCoinInfo(String holdings, String average, String profit);
        void updateCoinInfo(String coinInfo, int idx);
        void updateNotice(String notice);
    }

    interface Presenter {
        void buySelectedCoin(String selectedCoinAndAmount) throws IOException;
        void sellSelectedCoin(String selectedCoinAndAmount) throws IOException;
    }
}
