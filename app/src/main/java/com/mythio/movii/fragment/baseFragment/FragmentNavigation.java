package com.mythio.movii.fragment.baseFragment;

public interface FragmentNavigation {

    interface View {
        void attachPresenter(Presenter presenter);
    }

    interface Presenter {
        void addFragment(BaseFragment fragment);
    }
}
