package com.example.instagramclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.Post;
import com.example.instagramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    public ProfileFragment() {
        // Required empty public constructor
        super();
    }

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> fetchedPosts, ParseException e) {
                Log.i(TAG, "here 2");
                if (e != null) {
                    Log.e(TAG, "Query gone wrong");
                    return;
                }
                for (Post post : fetchedPosts) {
                    Log.i(TAG, post.getDescription() + " by " + post.getUser());
                }
                posts.addAll(fetchedPosts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}