/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HardCodedVals;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Connor
 */
public class PostPairs 
{
    //List<int> stickerIDs = new List<int>();
    private String username;
    public List<Integer> stickerIDs = new ArrayList<>();
    
    public void insertPostPairs(String username, List<Integer> stickerIDs)
    {
        this.username = username;
        this.stickerIDs = stickerIDs;
    }

    public List<Integer> getStickerIDs()
    {
        return this.stickerIDs;
    }

    public String getName(){
        return this.username;
    }
}
