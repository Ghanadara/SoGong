package com.example.sogong;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    static HashMap<String, String> userlist = new HashMap<>();
    static ArrayList<Post> postlist = new ArrayList<>();

    public HashMap<String, String> getUserlist() {
        return userlist;
    }

    public void setUserlist(HashMap<String, String> userlist) {
        this.userlist = userlist;
    }

    public static boolean addUser(String id, String pw) {
        if (userlist.containsKey(id)) {
            return true;
        } else {
            userlist.put(id, pw);
            return false;
        }
    }

    public static boolean findUser(String id, String pw) {
        if (userlist.containsKey(id)) {
            if (pw.equals(userlist.get(id))) {
                return true;
            } else return false;
        } else return false;
    }
    public static void addPost(String id, String title,String content){
        Post post = new Post(id,title,content);
        postlist.add(post);
    }

    public static ArrayList<Post> getPostlist() {
        return postlist;
    }
}