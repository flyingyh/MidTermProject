package com.qf.midtermproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.bean.CommentBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */

public class LvCommentAdapter extends BaseAdapter {
    private List<CommentBean> commentData;
    private Context context;

    public LvCommentAdapter(List<CommentBean> commentData, Context context) {
        this.commentData = commentData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentData.size();
    }

    @Override
    public Object getItem(int position) {
        return commentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   class ViewHolder{
       TextView tv_name,tv_order,tv_info;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.lvitem_comment_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_order = (TextView)convertView.findViewById(R.id.tv_order);
            viewHolder.tv_info = (TextView)convertView.findViewById(R.id.tv_info);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CommentBean bean = commentData.get(position);
        viewHolder.tv_name.setText(bean.getUsername());
        viewHolder.tv_order.setText(bean.getFloor());
        viewHolder.tv_info.setText(bean.getMsg());

        return convertView;
    }
}
