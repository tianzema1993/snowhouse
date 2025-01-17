package com.neu.snowhouse.api;

import com.neu.snowhouse.model.request.CommentRequestModel;
import com.neu.snowhouse.model.response.CommentResponseModel;
import com.neu.snowhouse.model.response.LiteMountainResponseModel;
import com.neu.snowhouse.model.response.LitePostResponseModel;
import com.neu.snowhouse.model.response.MountainResponseModel;
import com.neu.snowhouse.model.response.PostResponseModel;
import com.neu.snowhouse.model.request.UserLoginRequestModel;
import com.neu.snowhouse.model.request.UserRegisterRequestModel;
import com.neu.snowhouse.model.request.UserResetPasswordRequestModel;
import com.neu.snowhouse.model.request.UserDropAccountRequestModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface API {
    // POST request to login the user
    @POST("user/login")
    Call<ResponseBody> loginUser(@Body UserLoginRequestModel user);

    // POST request to register the user
    @POST("user/register")
    Call<ResponseBody> registerUser(@Body UserRegisterRequestModel user);

    // POST request to reset the password
    @POST("user/resetPassword")
    Call<ResponseBody> resetPassword(@Body UserResetPasswordRequestModel user);

    // POST request to drop the account
    @HTTP(method = "DELETE", path = "user/dropAccount", hasBody = true)
    Call<ResponseBody> deleteAccount(@Body UserDropAccountRequestModel user);

    // POST request to upload a post without image to the backend
    @Multipart
    @POST("post/")
    Call<ResponseBody> uploadRawPost(@Part("post") RequestBody jsonPost);

    // POST request to upload a post with image to the backend
    @Multipart
    @POST("post/withImage")
    Call<ResponseBody> uploadPostWithImage(@Part MultipartBody.Part image, @Part("post") RequestBody jsonPost);

    // GET request to get a post by its ID
    @GET("post/{currentUser}/{postId}")
    Call<PostResponseModel> getPostById(@Path("currentUser") String currentUser, @Path("postId") int postId);

    // DELETE a post by its id
    @HTTP(method = "DELETE", path = "post/{currentUser}/{postId}", hasBody = true)
    Call<ResponseBody> deletePostById(@Path("currentUser") String currentUser, @Path("postId") int postId);

    // GET request to get all posts
    @GET("post/all")
    Call<List<LitePostResponseModel>> getAllPosts();

    // GET request to search posts
    @GET("post/search/{query}")
    Call<List<LitePostResponseModel>> searchPosts(@Path("query") String query);

    // POST request user's all post
    @GET("post/{currentUser}/all")
    Call<List<LitePostResponseModel>> getMyPosts(@Path("currentUser") String currentUser);

    // GET request to get all posts with new comments
    @GET("post/{currentUser}/newComments")
    Call<List<LitePostResponseModel>> getPostsWithNewComments(@Path("currentUser") String currentUser);

    // POST request to like the post
    @POST("post/like/{postId}/{currentUser}")
    Call<ResponseBody> likePost(@Path("postId") int postId, @Path("currentUser") String currentUser);

    // POST request to dislike the post
    @POST("post/dislike/{postId}/{currentUser}")
    Call<ResponseBody> dislikePost(@Path("postId") int postId, @Path("currentUser") String currentUser);

    // POST request to add a new Comment
    @POST("comment/")
    Call<CommentResponseModel> addComment(@Body CommentRequestModel commentRequestModel);

    // GET request to get a comment by commentId
    @GET("comment/{currentUser}/{commentId}")
    Call<CommentResponseModel> getCommentById(@Path("commentId") int commentId, @Path("currentUser") String currentUser);

    // GET request to get all comments by postId
    @GET("comment/all/{postId}/{currentUser}")
    Call<List<CommentResponseModel>> getCommentsByPostId(@Path("postId") int postId, @Path("currentUser") String currentUser);

    // POST request to like the comment
    @POST("comment/like/{commentId}/{currentUser}")
    Call<ResponseBody> likeComment(@Path("commentId") int commentId, @Path("currentUser") String currentUser);

    // POST request to dislike the comment
    @POST("comment/dislike/{commentId}/{currentUser}")
    Call<ResponseBody> dislikeComment(@Path("commentId") int commentId, @Path("currentUser") String currentUser);

    // GET request to get all mountains
    @GET("mountain/all")
    Call<List<LiteMountainResponseModel>> getAllMountains();

    // GET request to get a mountain by its ID
    @GET("mountain/{mountainId}/{currentUser}")
    Call<MountainResponseModel> getMountainById(@Path("mountainId") int mountainId, @Path("currentUser") String currentUser);

    // POST request to rate the mountain
    @PUT("mountain/{mountainId}/{currentUser}/{rating}")
    Call<MountainResponseModel> rateMountain(@Path("mountainId") int mountainId, @Path("currentUser") String currentUser, @Path("rating") int rating);
}
