package pars.androidquizapp.questions;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pars.androidquizapp.R;
import pars.androidquizapp.questions.QuestionsAdapter.OnQuestionClicked;
import pars.androidquizapp.data.MainDatabase;
import pars.androidquizapp.data.Question;


public class QuestionsFragment extends Fragment implements QuestionsContract.View{

    private QuestionsContract.Presenter mPresenter;
    private MainDatabase database;
    private List<Question> questions = new ArrayList<>();
    private QuestionsAdapter questionsAdapter;
    private String category;

    @BindView(R.id.tv_empty)
    TextView emptyTextView;
    @BindView(R.id.question_recycler_view)
    RecyclerView recyclerView;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    public static QuestionsFragment newInstance() {
        return new QuestionsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = MainDatabase.getInstance(getActivity());
        mPresenter = new QuestionsPresenter(this, database.questionDao());
    }

    @Override
    public void setPresenter(QuestionsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, root);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        questionsAdapter = new QuestionsAdapter(getActivity(), questions, mItemListener);
        recyclerView.setAdapter(questionsAdapter);


        return root;
    }

    /**
     * Listener for clicks on categories in the RecyclerView.
     */
    OnQuestionClicked mItemListener = new OnQuestionClicked() {
        @Override
        public void onQuestionClick(Question question) {

        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        category = getActivity().getIntent().getExtras().getString("category");
        if(questions == null){
            showEmptyMessage();
        }
        mPresenter.fetchQuestions(category);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void showAllQuestions(List<Question> questions) {
        emptyTextView.setVisibility(View.GONE);
        questionsAdapter.setValues(questions);

    }

    @Override
    public void showEmptyMessage() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

}