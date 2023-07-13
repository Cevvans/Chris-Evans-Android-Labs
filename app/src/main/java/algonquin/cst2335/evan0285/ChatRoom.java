package algonquin.cst2335.evan0285;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.evan0285.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.evan0285.databinding.ReceiveMessageBinding;
import algonquin.cst2335.evan0285.databinding.SentMessageBinding;
import algonquin.cst2335.evan0285.src.ChatMessages;
import algonquin.cst2335.evan0285.src.ChatRoomViewModel;


public class ChatRoom extends AppCompatActivity {
    private ActivityChatRoomBinding binding;

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
        public void bind(ChatMessages chatMessage) {
        messageText.setText((chatMessage.getMessage()));
        timeText.setText(chatMessage.getTimeSent());
        }
    }


    ChatRoomViewModel chatmodel;

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy \nhh:mm:ss a");
        return sdf.format(new Date());
    }


    ArrayList<ChatMessages> messages = new ArrayList<>();

    private RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        chatmodel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatmodel.messages.getValue();
        if(messages == null){
            chatmodel.messages.setValue(messages = new ArrayList<ChatMessages>());
        }

        binding.sendButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            String timeSent = getCurrentTime();
            boolean isSentButton = true;
            ChatMessages chatMessage = new ChatMessages(message, timeSent, isSentButton);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            binding.textInput.setText("");
        });

        binding.receiveButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            String timeSent = getCurrentTime();
            boolean isSentButton = false;
            ChatMessages chatMessage = new ChatMessages(message, timeSent, isSentButton);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            binding.textInput.setText("");
        });







        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
         binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(inflater, parent, false);
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(inflater, parent, false);
                    return new MyRowHolder(binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
               ChatMessages chatMessage = messages.get(position);
                holder.bind(chatMessage);

            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
             public int getItemViewType(int position){
                ChatMessages chatMessage = messages.get(position);
                if(chatMessage.isSentButton()){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });



    }
}