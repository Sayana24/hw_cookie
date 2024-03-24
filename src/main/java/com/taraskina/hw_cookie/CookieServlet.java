package com.taraskina.hw_cookie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CookieServlet", value = "/cookie-servlet")
public class CookieServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        ArrayList<Cookie> cookies = new ArrayList<>();

        String myStr = "";
        ArrayList<String> setOfStr = new ArrayList<>();
        List<String> mySetOfStr = new ArrayList<>();
        int k=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\sayan\\IdeaProjects\\hw_cookie\\src\\main\\resources\\notes.txt"));
            String line = reader.readLine();
            while (line != null) {
                char[] charArray = line.toCharArray();
                for (char c : charArray) {
                    if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                        myStr = myStr + c;
                    } else {
                        if (myStr != "") {
                            setOfStr.add(myStr);
                            myStr = "";
                        }
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int max = 0;
        for (int i = 0; i < setOfStr.size(); i++) {
            if (setOfStr.get(i).length() > max)
                max = setOfStr.get(i).length();
        }
        for (int i = 0; i < setOfStr.size(); i++) {
            if (setOfStr.get(i).length() == max){
                mySetOfStr.add(setOfStr.get(i));
            }
        }
        out.println("Максимальное число последовательных символов - не букв:  " + max);
        String[] arr = mySetOfStr.toArray(new String[0]);
        request.setAttribute("arr", arr);
        out.println("<br>");
        out.println("Количество таких последовательностей:  " + arr.length);

        for (int i = 0; i < arr.length; i++) {
            Cookie cookie = new Cookie("cookie", arr[i]);
            cookies.add(cookie);
        }
        for (int i = 0; i < cookies.size(); i++) {
            out.println("<h1>" + "Name:  " + cookies.get(i).getName() +
                    "     " + "Value:  " + cookies.get(i).getValue() + "</h1>");
        }
        out.println("</body></html>");
        out.close();
    }
}