package com.example.search_example_android;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {
  
  private List<String>    _data;
  private LayoutInflater  _inflater;
  
  public MainListAdapter(Context context, List<String> data){
    super();
    _data = data;
    _inflater = LayoutInflater.from(context);
  }
  
  @Override
  public int getCount() {
    return _data.size();
  }

  @Override
  public Object getItem(int position) {
    return _data.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    
    if (convertView == null){
      convertView = _inflater.inflate(R.layout.li_main_list, null);
      holder = new ViewHolder();
      holder.nameTextView = (TextView) convertView .findViewById(R.id.name_TV);
      convertView.setTag(holder);
    }else{
      holder = (ViewHolder) convertView.getTag();
    }
    
    holder.nameTextView.setText(_data.get(position));
    
    return convertView;
  }
  
  @Override
  public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
  }
  
  // Public class
  // -----------------------------------------------------------------------------------------
  public void setData(List<String> data){
    _data = data;
    notifyDataSetChanged();
  }
  
  // ViewHolder class
  // -----------------------------------------------------------------------------------------
  static class ViewHolder {
    TextView nameTextView;
  }
}
