package com.example.bysj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bysj.R;
import com.example.bysj.clicklistener.OnDelBtnClickListener;
import com.example.bysj.entity.TeacherAndCourse;

import java.util.List;

public class LvTeacherCourseAdapter extends BaseAdapter {
    private Context context;
    private List<TeacherAndCourse> teacherAndCourseList;
    private OnDelBtnClickListener onDelBtnClickListener;//删除按钮

    public LvTeacherCourseAdapter() {
    }

    public LvTeacherCourseAdapter(Context context, List<TeacherAndCourse> teacherAndCourseList) {
        this.context = context;
        this.teacherAndCourseList = teacherAndCourseList;
        Log.i("数据适配器","用户数量:"+teacherAndCourseList.size());
    }

    public void setTeacherAndCourseList(List<TeacherAndCourse> teacherAndCourseList) {
        this.teacherAndCourseList = teacherAndCourseList;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @Override
    public int getCount() {
        return teacherAndCourseList.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherAndCourseList.get(position);
    }

    @Override

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.course_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_coursename = convertView.findViewById(R.id.tv_coursename);

            viewHolder.iv_del = convertView.findViewById(R.id.iv_del);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TeacherAndCourse item = teacherAndCourseList.get(position);
        viewHolder.tv_coursename.setText(item.getCoursename());

        viewHolder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelBtnClickListener.onDelBtnClick(v,position);
            }
        });

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_coursename;
        private ImageView iv_del;
    }

}
