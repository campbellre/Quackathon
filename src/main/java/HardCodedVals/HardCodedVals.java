/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HardCodedVals;
import static java.lang.System.out;
import java.util.*;
import javafx.util.Pair;
import static java.lang.System.out;

/**
 *
 * @author Connor
 */
public class HardCodedVals 
{
  
    IDPairs[] pva = new IDPairs[3];
    UserPairs[] upa = new UserPairs[3];
    List<PostPairs> ppa = new ArrayList<PostPairs>();
    
    private void hardCodeVals()
    {
        pva[0] = new IDPairs();
        pva[0].insertVals("Connor", 0);
        pva[1] = new IDPairs();
        pva[1].insertVals("Ryan", 1);
        pva[2] = new IDPairs();
        pva[2].insertVals("Kate", 2);
        
        upa[0] = new UserPairs();
        upa[0].insertUser("Connor", "Looks Smart But Is Very Fucking Stupid");
        upa[1] = new UserPairs();
        upa[1].insertUser("Ryan", "Vape Nation");
        upa[2] = new UserPairs();
        upa[2].insertUser("Kate", "Polish Person");
        
        List<Integer> stickerIDs = Arrays.asList(1);
        PostPairs pp = new PostPairs();
        pp.insertPostPairs("Ryan", stickerIDs);
        ppa.add(pp);
        
        stickerIDs = Arrays.asList(0, 3, 2, 4);
        
        pp.insertPostPairs("Connor", stickerIDs);
        ppa.add(pp);
        stickerIDs = Arrays.asList(1, 5, 3);
        pp = new PostPairs();
        pp.insertPostPairs("Kate", stickerIDs);
        ppa.add(pp);
        stickerIDs = Arrays.asList(6,2,1);
        pp = new PostPairs();
        pp.insertPostPairs("Kate", stickerIDs);
        ppa.add(pp);
        stickerIDs = Arrays.asList(2, 4, 1);
        pp = new PostPairs();
        pp.insertPostPairs("Connor", stickerIDs);
        ppa.add(pp);        
    }
    
    private void insertPost(String username, List<Integer> stickerIDs)
    {
        PostPairs pp = new PostPairs();
        pp.insertPostPairs(username, stickerIDs);
        ppa.add(pp);
        
    }
    
    private void getUserPosts(String username)
    {
        
        List<PostPairs> posts = new ArrayList<>();
        
        List<Integer> stickerIDs = new ArrayList<>();
        
        for(int l = 0; l < ppa.size(); l++)
        {
          if(ppa.get(l).username == username)
          {
              PostPairs pp = new PostPairs();
              pp.insertPostPairs(username, ppa.get(l).stickerIDs);
              posts.add(pp);
          }
        }
        
        for(int k = 0; k < posts.size(); k++)
        {
            System.out.println("Post... " + Integer.toString(posts.get(k).stickerIDs.get(1)));
        }
    }
   
    private Boolean checkUserValid(String username, String password)
    {
        for(int i = 0; i < pva.length; i++)
        {
            if(pva[i].name == username)
            {
                for(int k = 0; k < upa.length; k++)
                {
                    if(upa[k].name == username)
                    {
                        if(upa[k].password == password)
                        {
                            System.out.println("True");
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
 
   
    public static void main(String[] args) 
    {
        // TODO code application logic here
        HardCodedVals hcv = new HardCodedVals();
        
        hcv.hardCodeVals();
        hcv.checkUserValid("Ryasn", "Vape Nation");
        hcv.getUserPosts("Kate");
  
        hcv.insertPost("Kate", Arrays.asList(1, 9, 2));
        hcv.getUserPosts("Kate");
        
        
    }
    
}
