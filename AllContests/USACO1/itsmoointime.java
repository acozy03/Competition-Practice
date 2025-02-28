package AllContests.USACO1;
import java.util.*;

public class itsmoointime {
    static final int MAXN = 1000005;
    static int[] a = new int[MAXN], pref = new int[MAXN], cnt = new int[MAXN];
    static long ans = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }
        
        for (int i = 1; i <= n; i++) {
            pref[i] = pref[i - 1];
            cnt[a[i]]++;
            if (cnt[a[i]] == 1) {
                pref[i]++;
            }
        }
        
        Arrays.fill(cnt, 0);
        
        for (int i = n; i >= 1; i--) {
            cnt[a[i]]++;
            if (cnt[a[i]] == 2) {
                ans += pref[i - 1];
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (cnt[i] >= 3) {
                ans--;
            }
        }
        
        System.out.println(ans);
        scanner.close();
    }
}
