import java.util.ArrayList;
import java.util.List;

public class interview {
    public static void main(String[] args) {
        String str="123,123121,123123";
        String[] temp=helper(str,',');
        for(int i=0;i< temp.length;i++){
            System.out.println(temp[i]);
        }
    }
    static String [] helper(String input,char character){
        List<String> res=new ArrayList<>();
        if(input.equals("")||input==null) return new String[0];
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<input.length();i++){

            if(input.charAt(i)!=character){
                sb.append(input.charAt(i));
            }
            else if(sb.length()!=0){
                res.add(sb.toString());
                sb=new StringBuilder();
            }
        }
        res.add(sb.toString());
        return res.toArray(new String[res.size()]);
    }
}
