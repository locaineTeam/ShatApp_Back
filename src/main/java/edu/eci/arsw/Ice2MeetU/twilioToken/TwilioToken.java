package edu.eci.arsw.Ice2MeetU.twilioToken;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.jwt.accesstoken.VoiceGrant;
import edu.eci.arsw.Ice2MeetU.entities.Token;
import org.springframework.stereotype.Service;

@Service
public class TwilioToken {
    public Token getToken(String user){

        String twilioAccountSid = "AC4ac6afd395a417a6a61179945e8abef0";
        String twilioApiKey = "SKc346bdc0cabf5c15a9cb850024acd47a";
        String twilioApiSecret = "V7gthu7BMNkuxVqQyQrzJLf0r9rO8fnn";

        String serviceSid = "IS8ff7b1767df04f65b607dec19e238920";
        String outgoingApplicationSid = "AP32c3fbfcb26ae1d41099f5372c7020c1";
        String identity = user;

        ChatGrant grant = new ChatGrant();
        grant.setServiceSid(serviceSid);

        // Create Voice grant
        VoiceGrant grantVoice = new VoiceGrant();
        grantVoice.setOutgoingApplicationSid(outgoingApplicationSid);

        // Optional: add to allow incoming calls
        grantVoice.setIncomingAllow(true);


        VideoGrant grantVideo = new VideoGrant().setRoom("cool room");

        AccessToken token = new AccessToken.Builder(twilioAccountSid, twilioApiKey, twilioApiSecret)
                .identity(identity).grant(grant).grant(grantVoice).grant(grantVideo).build();

        //System.out.println(token.toJwt());
        Token t = new Token(token.toJwt());




        return t;
    }
}