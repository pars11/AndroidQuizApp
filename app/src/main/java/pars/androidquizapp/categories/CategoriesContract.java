package pars.androidquizapp.categories;

import java.util.List;

import pars.androidquizapp.BasePresenter;
import pars.androidquizapp.BaseView;
import pars.androidquizapp.data.Category;


public interface CategoriesContract {

    interface View extends BaseView<CategoriesContract.Presenter> {

        void showCategories(List<Category> category);

        void showAddCategory();

        void showEmptyMessage();

        void showCategoryToUpdate(Category category);

    }

    interface Presenter extends BasePresenter {

        void addNewCategory();

        void saveNewCategory(String category);

        void fetchCategories();

        void updateCategory(long id, String category);

        void getCategoryToUpdate(Category category);

        void deleteCategory(long categoryId);

    }
}
