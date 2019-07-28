import java.util.List;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    private ItemRepository itemRepository;
    private SalesPromotionRepository salesPromotionRepository;

    public App(ItemRepository itemRepository, SalesPromotionRepository salesPromotionRepository) {
        this.itemRepository = itemRepository;
        this.salesPromotionRepository = salesPromotionRepository;
    }

    public String bestCharge(List<String> inputs) {
        //TODO: write code here
        StringBuilder res = new StringBuilder();
        res.append("============= 订餐明细 =============\n");
        int totle = 0;
        int totle1 = 0;
        int totle2 = 0;
        for (String i : inputs) {
            String[] temp = i.split(" ");
            String id = temp[0];
            String num = temp[2];
            String name = this.itemRepository.getNameById(id);
            int price = this.itemRepository.getPriceById(id);
            int price1 = this.salesPromotionRepository.getPromotion2(id, price);
            int tol = Integer.parseInt(num) * price;
            totle += tol;
            totle2 += Integer.parseInt(num) * price1;
            res.append(name + " x " + num + " = " + tol + "元\n");
        }
        res.append("-----------------------------------\n");
        if( totle>totle1&&totle>totle2){
            res.append("使用优惠:\n");
            totle1 = this.salesPromotionRepository.getPromotion1(totle);
            if (totle1 <= totle2 ) {
                res.append("满30减6元，省6元\n");
                res.append("-----------------------------------\n");
                res.append("总计：" + totle1 + "元\n");
            } else {
                res.append("指定菜品半价(黄焖鸡，凉皮)，省13元\n");
                res.append("-----------------------------------\n");
                res.append("总计：" + totle2 + "元\n");
            }
        }else{
            res.append("总计：" + totle + "元\n");
        }
        res.append("===================================");
        return res.toString();
    }
}
