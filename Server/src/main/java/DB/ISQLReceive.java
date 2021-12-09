package DB;

import SchoolOrg.Receive;

import java.util.ArrayList;

public interface ISQLReceive {
    ArrayList<Receive> get();
    ArrayList<Receive> getChart();
}
