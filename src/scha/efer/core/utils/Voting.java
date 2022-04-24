package scha.efer.core.utils;

import java.util.*;

public class Voting
{
    public static Integer yes;
    public static Integer no;
    public static Boolean isVotingEnabled;
    public static HashMap<UUID, Boolean> isPlayerUsedVote;

    static {
        Voting.yes = 0;
        Voting.no = 0;
        Voting.isVotingEnabled = false;
        Voting.isPlayerUsedVote = new HashMap<UUID, Boolean>();
    }
}
