package org.server;

import io.swagger.client.ApiClient;
        import io.swagger.client.ApiException;
        import io.swagger.client.ApiResponse;
        import io.swagger.client.api.SwipeApi;
        import io.swagger.client.model.SwipeDetails;

public class Client implements Runnable{

    private final String baseUrl;

    public Client(String baseUrl) {
        this.baseUrl = baseUrl;
    }



    public void run() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(baseUrl);
        SwipeApi swipeApi = new SwipeApi(apiClient);

        SwipeDetails swipe = new SwipeDetails();
        swipe.setSwiper(getRandomSwiper());
        swipe.setSwipee(getRandomSwipee());
        swipe.setComment(getRandomComment());

        try {


            ApiResponse swipeResponse = swipeApi.swipeWithHttpInfo(swipe,getRandomSwipe());
            int statusCode = swipeResponse.getStatusCode();
            if(statusCode == 200 || statusCode == 201){
                System.out.println("Success: " + String.valueOf(statusCode));
            }

        } catch (ApiException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private String getRandomSwipe() {
        // Generate a random swipe (either "left" or "right")
        return Math.random() < 0.5 ? "left" : "right";
    }

    private String getRandomSwiper() {
        // Generate a random swiper between 1 and 5000
        return String.valueOf ((Math.random() * 5000)+ 1);
    }

    private String getRandomSwipee() {
        // Generate a random swipee between 1 and 1000000
        return String.valueOf ((Math.random() * 1000000)+ 1);
    }

    private String getRandomComment() {
        // Generate a random string of 256 characters
        char[] chars = new char[256];
        for (int i = 0; i < 256; i++) {
            chars[i] = (char) (Math.random() * 256);
        }
        return new String(chars);
    }
}
