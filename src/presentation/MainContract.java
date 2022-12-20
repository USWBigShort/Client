package presentation;

import java.io.IOException;

public interface MainContract {
    interface View {
        void setAvailableAssets(String assets);
        void addCoin(String coinInfo);
        void setSelectedCoinInfo(String holdings, String average, String profit);
        void updateCoinInfo(String coinInfo, int idx);
    }

    interface Presenter {
        void buySelectedCoin(String selectedCoinAndAmount) throws IOException;
        void sellSelectedCoin(String selectedCoinAndAmount) throws IOException;
    }
}
