package com.example.quizandregistration;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizandregistration.Modal.Questions;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Questions> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<Questions> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.activity_main, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvQuestions = (TextView) convertView.findViewById(R.id.questionsTxt);
            viewHolder.tvOptionA = (TextView) convertView.findViewById(R.id.button1);
            viewHolder.tvOptionB = (TextView) convertView.findViewById(R.id.button2);
            viewHolder.tvOptionC = (TextView) convertView.findViewById(R.id.button3);
            viewHolder.tvOptionD = (TextView) convertView.findViewById(R.id.button4);
            //viewHolder.tvWrongAnswers = (TextView) convertView.findViewById(R.id.tvWrongAnswer);
            //viewHolder.tvCorrectAnswers = (TextView) convertView.findViewById(R.id.tvCorrectAnswer);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        Questions person = data.get(position);//modify here
        viewHolder.tvQuestions.setText(person.getAnswer());//modify here
        viewHolder.tvOptionA.setText(person.getA());//modify here
        viewHolder.tvOptionB.setText(person.getB());//modify here
        viewHolder.tvOptionC.setText(person.getC());//modify here
        viewHolder.tvOptionD.setText(person.getD());//modify here
        return convertView;

    }
    static class ViewHolder {
        TextView tvQuestions;
        TextView tvOptionA;
        TextView tvOptionB;
        TextView tvOptionC;//These don't have to be same names as the Id's
        TextView tvOptionD;//These don't have to be same names as the Id's
        TextView tvWrongAnswers;//These don't have to be same names as the Id's
        TextView tvCorrectAnswers;//These don't have to be same names as the Id's
        //MODIFY THIS BLOCK OF CODE
        //TextView textViewTitle;

    }

}
