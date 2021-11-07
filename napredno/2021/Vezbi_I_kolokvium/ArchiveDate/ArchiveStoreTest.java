package ArchiveDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
                    * 60 * 1000));
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}

class ArchiveStore{
    private List<String> log;
    private List<Archive> archives;

    public ArchiveStore() {
        archives = new ArrayList<Archive>();
        log = new ArrayList<String >();
    }

    public String getLog() {
        String fullstr = "";
        for(String l : log){
            String replaced = l.replace("GMT", "UTC");
            fullstr+=replaced+"\n";
        }
        return fullstr;
    }

    public void archiveItem(Archive item, Date date){
        item.setDateArchived(date);
        archives.add(item);
        log.add(String.format("Item %d archived at %s",item.getId(),date.toString()));
    }

    public void openItem(int id, Date date) throws NonExistingItemException {
        for(Archive item : archives){
            if(item.getId()==id){
                if(item instanceof LockedArchive){
                    LockedArchive la = ((LockedArchive) item);
                    if(la.getDateToOpen().after(date))
                        log.add(String.format("Item %d cannot be opened before %s",item.getId(),la.getDateToOpen()));
                    else log.add(String.format("Item %d opened at %s",item.getId(),date));
                }
                else if(item instanceof SpecialArchive){
                    SpecialArchive sa = ((SpecialArchive) item);
                    if(sa.getOpened()<sa.getMaxOpen()){
                        log.add(String.format("Item %d opened at %s",item.getId(),date));
                        sa.open();
                    }else log.add(String.format("Item %d cannot be opened more than %d times",item.getId(),((SpecialArchive) item).getMaxOpen()));
                }
                return;
            }
        }
        throw new NonExistingItemException(id);
    }
}
class NonExistingItemException extends Exception {
    public NonExistingItemException(int id) {
        super("Item with id "+id+" doesn't exist");
    }
}
class Archive{
    private int id;
    private Date dateArchived;

    public Archive(int id) {
        this.id = id;
    }

    public void setDateArchived(Date dateArchived) {
        this.dateArchived = dateArchived;
    }

    public int getId() {
        return id;
    }
}
class LockedArchive extends Archive{
    private Date dateToOpen;

    public LockedArchive(int id, Date dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    public Date getDateToOpen() {
        return dateToOpen;
    }
}
class SpecialArchive extends Archive{
    private int maxOpen;
    private int opened;

    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        this.opened = 0;
    }

    public int getMaxOpen() {
        return maxOpen;
    }

    public int getOpened() {
        return opened;
    }
    public void open(){
        opened++;
    }
}