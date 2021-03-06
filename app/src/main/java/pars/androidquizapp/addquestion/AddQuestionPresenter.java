package pars.androidquizapp.addquestion;


import pars.androidquizapp.data.QuestionDao;
import pars.androidquizapp.data.Question;


public class AddQuestionPresenter implements AddQuestionContract.Presenter{

    private AddQuestionContract.view mView;
    private QuestionDao questionDao;

    public AddQuestionPresenter(AddQuestionContract.view mView, QuestionDao questionDao) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.questionDao = questionDao;
    }

    @Override
    public void saveQuestion(long categoryId, String question, String optionA,
                            String optionB, String optionC, String optionD, String answer) {

        Question questionParams = new Question(categoryId, question, optionA,
                optionB, optionC, optionD, answer);
        questionDao.insert(questionParams);
    }

    @Override
    public void fetchQuestionToUpdate(long id) {
        Question questionList = questionDao.queryQuestion(id);
        mView.showQuestionToUpdate(questionList);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.updateQuestion(question);
    }


    @Override
    public void start() {

    }
}
