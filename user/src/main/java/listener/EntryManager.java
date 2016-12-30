package listener;

/**
 * Created by pengshu on 2016/11/11.
 */
public class EntryManager {
    public static void main(String[] args) {
        EntryManager eventManager = new EntryManager();
        Entry entry = new Entry();
        entry.setId(1);
        entry.setName("标题");

        eventManager.addEntry(entry);  //创建

//        eventManager.modifyEntry(entry); //修改

//        eventManager.deleteEntry(entry); //删除

    }

    public void addEntry(Entry entry) {
        System.out.println("add entry ...id=" + entry.getId());

        // 事件分发
        EntryEvent event = new EntryEvent(EntryEvent.ENTRY_ADDED, entry, null);
        EntryEventDispatcher.getInstance().dispatchEvent(event);

    }

    public void deleteEntry(Entry entry) {
        System.out.println("delete entry ...id=" + entry.getId());

        // 事件分发
        EntryEvent event = new EntryEvent(EntryEvent.ENTRY_DELETED, entry, null);
        EntryEventDispatcher.getInstance().dispatchEvent(event);

    }

    public void modifyEntry(Entry entry) {
        System.out.println("modify entry ...id=" + entry.getId());
        // 事件分发
        EntryEvent event = new EntryEvent(EntryEvent.ENTRY_MODIFIED, entry, null);
        EntryEventDispatcher.getInstance().dispatchEvent(event);

    }

}
