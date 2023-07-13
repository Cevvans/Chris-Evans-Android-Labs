package algonquin.cst2335.evan0285.src;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import algonquin.cst2335.evan0285.src.ChatMessages;
import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel {
        public MutableLiveData<ArrayList<ChatMessages>> messages = new MutableLiveData< >();
}
