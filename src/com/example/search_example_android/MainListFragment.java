package com.example.search_example_android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<String>>, OnQueryTextListener {
  
  
  private String _curFilter;        // if non-null, this is the current filter the user has provided.
  private MainListAdapter _adapter; // the adapter
  
  // ListFragment methods
  // -----------------------------------------------------------------------------------------
  /*@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main_list, container, false);
  }*/
  
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
    // set an empty filter
    _curFilter = null;
    
    // has menu
    setHasOptionsMenu(true);
    
    // set empty adapter
    _adapter = new MainListAdapter(getActivity(), new ArrayList<String>());
    setListAdapter(_adapter);
    
    // start out with a progress indicator.
    setListShown(false);
    
    // prepare the loader.  Either re-connect with an existing one, or start a new one.
    getLoaderManager().initLoader(0, null, this);
  }

  @Override
  public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
    // Inflate the options menu from XML
    inflater.inflate(R.menu.main_list_menu, menu);

    // Get the SearchView and set the searchable configuration
    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search) .getActionView();
    searchView.setOnQueryTextListener(this);
  }
  
  // LoaderManager methods
  // -----------------------------------------------------------------------------------------
  @Override
  public Loader<List<String>> onCreateLoader(int id, Bundle args) {
    return new MainListLoader(getActivity(), _curFilter);
  }

  @Override
  public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
    // Set the new data in the adapter.
    _adapter.setData(data);

    // The list should now be shown.
    if (isResumed()) {
      setListShown(true);
    } else {
      setListShownNoAnimation(true);
    }
  }

  @Override
  public void onLoaderReset(Loader<List<String>> loader) {
    // Android guides set data to null but I need check
    // what happen when I set data as null
    _adapter.setData(new ArrayList<String>());
  }
  
  // OnQueryTextListener methods
  // -----------------------------------------------------------------------------------------
  @Override
  public boolean onQueryTextChange(String newText) {
    // Called when the action bar search text has changed. sUpdate
    // the search filter, and restart the loader to do a new query
    // with this filter.
    _curFilter = !TextUtils.isEmpty(newText) ? newText : null;
    _adapter.setQuery(_curFilter);
    getLoaderManager().restartLoader(0, null, this);
    return true;
  }

  @Override
  public boolean onQueryTextSubmit(String query) {
    // Don't care about this.
    return false;
  }
  
  
  
  
  
}
