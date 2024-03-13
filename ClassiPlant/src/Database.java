import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection connect() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/ClassiPlant";
        String username = "root";
        String password = "Moose00101011000";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    ArrayList<PlantName> getAllNames() {
        ArrayList<PlantName> myPNames = new ArrayList<>();
        String sql = """
                SELECT common_name, scientific_name
                FROM Plant_name
                """;
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String commonName = rs.getString("common_name");
                String scientificName = rs.getString("scientific_name");
                PlantName Pname = new PlantName(commonName, scientificName);
                myPNames.add(Pname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myPNames;
    }

    PlantInfo getPlantInfo(final String plantName) {
        PlantInfo plantInfo = null;
        String sql = """
                SELECT
                    pn.common_name,
                    pn.scientific_name,
                    fl.flower,
                    fl.blooming_season,
                    fl.petal_num,
                    fl.shape AS flower_shape,
                    fl.color AS flower_color,
                    fr.fruit,
                    fr.season AS fruit_season,
                    fr.color AS fruit_color,
                    fr.shape AS fruit_shape,
                    fr.size AS fruit_size,
                    lf.leaf_shape,
                    lf.leaf_margins,
                    lf.foliage_type,
                    lf.surface_texture,
                    lf.length,
                    sf.edibility,
                    sf.dangers
                FROM Plant_name pn
                LEFT JOIN Flower fl ON pn.nameID = fl.flower
                LEFT JOIN Fruit fr ON pn.nameID = fr.fruit
                LEFT JOIN Leaf lf ON pn.nameID = lf.leafID
                LEFT JOIN Safety sf ON pn.nameID = sf.safeID
                WHERE pn.common_name = ?;
                """;
        try {
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, plantName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String commonName = rs.getString("common_name");
                String scientificName = rs.getString("scientific_name");
                String flower = rs.getString("flower");
                String flowerSeason = rs.getString("blooming_season");
                int petalNum = rs.getInt("petal_num");
                String flowerShape = rs.getString("flower_shape");
                String flowerColor = rs.getString("flower_color");
                String fruit = rs.getString("fruit");
                String fruitSeason = rs.getString("fruit_season");
                String fruitColor = rs.getString("fruit_color");
                String fruitShape = rs.getString("fruit_shape");
                double fruitSize = rs.getDouble("fruit_size");
                String leafShape = rs.getString("leaf_shape");
                String leafMargins = rs.getString("leaf_margins");
                String foliage = rs.getString("foliage_type");
                String leafTexture = rs.getString("surface_texture");
                double leafLength = rs.getDouble("length");
                String edibility = rs.getString("edibility");
                String dangers = rs.getString("dangers");

                plantInfo = new PlantInfo(
                        commonName, scientificName, flower,
                        flowerSeason, petalNum, flowerShape, flowerColor, fruit,
                        fruitSeason, fruitColor, fruitShape, fruitSize,
                        leafShape, leafMargins, foliage, leafTexture, leafLength,
                        edibility, dangers
                );
            } else {
                System.out.println("""
                        Plant not found
                        it is also possible that the plant you are trying to gather
                        information about is not a native plant to Pierce County
                        """);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plantInfo;
    }

    ArrayList<PlantName> getPlant(final UserInfo userInfo) {
        ArrayList<PlantName> pNames = new ArrayList<>();
        String sql = """
                SELECT
                    pn.common_name,
                    pn.scientific_name,
                    (
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.height_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.fruit_color_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.fruit_shape_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.fruit_size_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.petal_num_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.flower_shape_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.flower_color_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.leaf_shape_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.leaf_margin_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.leaf_texture_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0) +
                        IFNULL(
                            (
                                CASE
                                    WHEN shrub_matches.leaf_length_match = 1 THEN 1
                                    ELSE 0
                                END
                            ), 0)
                    ) AS match_score
                FROM Plant_name pn
                LEFT JOIN (
                    SELECT
                        s.shrubID,
                        (
                            CASE
                                WHEN s.min <= ? AND s.max >= ? THEN 1
                                ELSE 0
                            END
                        ) AS height_match,
                        (
                            CASE
                                WHEN f.color = ? THEN 1
                                ELSE 0
                            END
                        ) AS fruit_color_match,
                        (
                            CASE
                                WHEN f.shape = ? THEN 1
                                ELSE 0
                            END
                        ) AS fruit_shape_match,
                        (
                            CASE
                                WHEN f.size <= ? + 2 AND f.size >= ? - 2 THEN 1
                                ELSE 0
                            END
                        ) AS fruit_size_match,
                        (
                            CASE
                                WHEN fl.petal_num = ? THEN 1
                                ELSE 0
                            END
                        ) AS petal_num_match,
                        (
                            CASE
                                WHEN fl.shape = ? THEN 1
                                ELSE 0
                            END
                        ) AS flower_shape_match,
                        (
                            CASE
                                WHEN fl.color = ? THEN 1
                                ELSE 0
                            END
                        ) AS flower_color_match,
                        (
                            CASE
                                WHEN l.leaf_shape = ? THEN 1
                                ELSE 0
                            END
                        ) AS leaf_shape_match,
                        (
                            CASE
                                WHEN l.leaf_margins = ? THEN 1
                                ELSE 0
                            END
                        ) AS leaf_margin_match,
                        (
                            CASE
                                WHEN l.surface_texture = ? THEN 1
                                ELSE 0
                            END
                        ) AS leaf_texture_match,
                        (
                            CASE
                                WHEN l.length = ? THEN 1
                                ELSE 0
                            END
                        ) AS leaf_length_match
                    FROM Shrub s
                    LEFT JOIN Flower fl ON s.flower = fl.flower
                    LEFT JOIN Fruit f ON s.fruit = f.fruit
                    LEFT JOIN Leaf l ON s.shrubID = l.leafID
                    WHERE s.min <= ? AND s.max >= ?
                ) shrub_matches ON pn.nameID = shrub_matches.shrubID
                ORDER BY match_score DESC
                LIMIT 3;
                
                """;
        try {
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, userInfo.getHeight());
            statement.setInt(2, userInfo.getHeight());
            statement.setString(3, userInfo.getFruitColor());
            statement.setString(4, userInfo.getFruitShape());
            statement.setDouble(5, userInfo.getFruitSize());
            statement.setDouble(6, userInfo.getFruitSize());
            statement.setInt(7, userInfo.getPetalNum());
            statement.setString(8, userInfo.getFlowerShape());
            statement.setString(9, userInfo.getFlowerColor());
            statement.setString(10, userInfo.getLeafShape());
            statement.setString(11, userInfo.getLeafMargins());
            statement.setString(12, userInfo.getLeafTexture());
            statement.setDouble(13, userInfo.getLeafLength());
            statement.setInt(14, userInfo.getHeight());
            statement.setInt(15, userInfo.getHeight());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String commonName = rs.getString("common_name");
                String scientificName = rs.getString("scientific_name");
                PlantName pName = new PlantName(commonName, scientificName);
                pNames.add(pName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pNames;
    }

}
