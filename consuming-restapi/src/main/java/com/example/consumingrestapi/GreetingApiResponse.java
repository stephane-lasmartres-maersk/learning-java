package com.example.consumingrestapi;

public class GreetingApiResponse {
    private Long id;
    private String greeting;
  
    public GreetingApiResponse() {
    }
  
    public Long getId() {
      return this.id;
    }
  
    public String getGreeting() {
      return this.greeting;
    }
  
    public void setId(Long id) {
      this.id = id;
    }
  
    public void setGreeting(String greeting) {
      this.greeting = greeting;
    }
  
    @Override
    public String toString() {
      return "Value{" +
          "id=" + id +
          ", greeting='" + greeting + '\'' +
          '}';
    }
}
