package com.example.search_example_android;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {
  
  private List<String>        _data;
  private LayoutInflater      _inflater;
  private String              _query;
  private ForegroundColorSpan _querySpan;
  
  public MainListAdapter(Context context, List<String> data){
    super();
    _data = data;
    _inflater = LayoutInflater.from(context);
    _query = null;
    _querySpan = new ForegroundColorSpan(Color.GREEN);
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
    
    String name = _data.get(position);
    int index = TextUtils.isEmpty(_query) ? -1 : name.toLowerCase().indexOf(_query.toLowerCase());
    if( index == -1 ){
      holder.nameTextView.setText(name);
    }else{
      SpannableStringBuilder ssb = new SpannableStringBuilder(name);
      ssb.setSpan(_querySpan, index, index + _query.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      holder.nameTextView.setText(ssb);
    }
    
    return convertView;
  }
  
  @Override
  public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
  }
  
  // Public methods
  // -----------------------------------------------------------------------------------------
  public void setData(List<String> data){
    _data = data;
    notifyDataSetChanged();
  }
  
  public String getQuery(){
    return _query;
  }
  
  public void setQuery(String query){
    _query = query;
  }
  
  // ViewHolder class
  // -----------------------------------------------------------------------------------------
  static class ViewHolder {
    TextView nameTextView;
  }
}
